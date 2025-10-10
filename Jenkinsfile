pipeline {
    agent any

    environment {
        DOCKERHUB_REPO = "nathanmathewv/scientific-calculator-java" // fix: add your username
        IMAGE_TAG = "${env.BUILD_ID}"
        PATH = "/usr/local/bin:/opt/homebrew/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps { 
                checkout scm 
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${DOCKERHUB_REPO}:${IMAGE_TAG} .'
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-user', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Push Image') {
            steps {
                sh 'docker push ${DOCKERHUB_REPO}:${IMAGE_TAG}'
            }
        }

        stage('Deploy') {
            steps {
                withEnv(["IMAGE_TAG=${env.BUILD_ID}"]) {
                    sh 'ansible-playbook -i ansible/inventory.ini ansible/deploy.yml'
                }
            }
        }

    }

    post {
        success {
            mail to: 'nathanmathewv@gmail.com',
                 subject: "Pipeline Success: ${currentBuild.fullDisplayName}",
                 body: "Maven + Docker + Ansible pipeline succeeded!"
        }
        failure {
            mail to: 'nathanmathewv@gmail.com',
                 subject: "Pipeline Failed: ${currentBuild.fullDisplayName}",
                 body: "Pipeline failed! Check Jenkins logs."
        }
        always {
            sh 'docker images | head -n 20'
        }
    }
}
