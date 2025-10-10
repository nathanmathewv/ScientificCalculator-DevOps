pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'docker-user'
        DOCKER_IMAGE_JAVA = 'scientific-calculator-java'
        MAVEN_HOME = tool 'Maven'
        JAVA_HOME = tool 'JDK-17'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from repository...'
                checkout scm
            }
        }

        stage('Build Java Application') {
            agent {
                docker {
                    image 'maven:3.9-openjdk-17'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
            steps {
                echo 'Building Java application with Maven...'
                dir('java-calculator') {
                    sh '''
                        mvn --version
                        mvn clean compile
                    '''
                }
            }
        }

        stage('Test Java Application') {
            agent {
                docker {
                    image 'maven:3.9-openjdk-17'
                }
            }
            steps {
                echo 'Running Java tests...'
                dir('java-calculator') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit 'java-calculator/target/surefire-reports/*.xml'
                    echo 'Java tests completed'
                }
            }
        }

        stage('Code Coverage') {
            agent {
                docker {
                    image 'maven:3.9-openjdk-17'
                }
            }
            steps {
                echo 'Generating code coverage report...'
                dir('java-calculator') {
                    sh 'mvn jacoco:report'
                }
            }
            post {
                always {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'java-calculator/target/site/jacoco',
                        reportFiles: 'index.html',
                        reportName: 'JaCoCo Coverage Report'
                    ])
                }
            }
        }

        stage('Package Java Application') {
            agent {
                docker {
                    image 'maven:3.9-openjdk-17'
                }
            }
            steps {
                echo 'Packaging Java application as JAR...'
                dir('java-calculator') {
                    sh 'mvn package -DskipTests'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: 'java-calculator/target/*.jar', fingerprint: true
                }
            }
        }

        stage('Build Docker Image') {
            agent any
            steps {
                script {
                    echo 'Building Docker image for Java application...'
                    dir('java-calculator') {
                        sh "docker build -t ${DOCKER_IMAGE_JAVA}:${BUILD_NUMBER} ."
                        sh "docker tag ${DOCKER_IMAGE_JAVA}:${BUILD_NUMBER} ${DOCKER_IMAGE_JAVA}:latest"
                    }
                }
            }
        }

        stage('Push Docker Image') {
            agent any
            steps {
                script {
                    echo 'Pushing Docker image to registry...'
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_CREDENTIALS_ID) {
                        sh "docker push ${DOCKER_IMAGE_JAVA}:${BUILD_NUMBER}"
                        sh "docker push ${DOCKER_IMAGE_JAVA}:latest"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deployment stage - configure based on your strategy'
                echo "Java Application: ${DOCKER_IMAGE_JAVA}:${BUILD_NUMBER}"
                echo "Run using: docker run -d -p 8080:8080 ${DOCKER_IMAGE_JAVA}:${BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
        success {
            echo 'Pipeline executed successfully!'
            mail to: 'nathanmathewv@gmail.com',
                 subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                 body: "Build Status: SUCCESS\nJob: ${env.JOB_NAME}\nBuild Number: ${env.BUILD_NUMBER}\nBuild URL: ${env.BUILD_URL}"
        }
        failure {
            echo 'Pipeline execution failed!'
            mail to: 'nathanmathewv@gmail.com',
                 subject: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                 body: "Build Status: FAILURE\nJob: ${env.JOB_NAME}\nBuild Number: ${env.BUILD_NUMBER}\nBuild URL: ${env.BUILD_URL}\nCheck console output for details."
        }
    }
}
