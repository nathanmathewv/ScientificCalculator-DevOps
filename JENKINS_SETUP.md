# Jenkins CI/CD Setup for Scientific Calculator

This document explains how to set up Jenkins for the Scientific Calculator project.

## Prerequisites

1. **Jenkins installed** (version 2.300+)
2. **Docker installed** on Jenkins server
3. **Required Jenkins Plugins**:
   - Docker Pipeline Plugin
   - JUnit Plugin
   - HTML Publisher Plugin
   - Email Extension Plugin
   - Git Plugin

## Jenkins Configuration

### 1. Install Required Plugins

Go to **Manage Jenkins** → **Manage Plugins** → **Available** and install:
- Docker Pipeline
- JUnit
- HTML Publisher
- Email Extension Plugin
- Pipeline: Stage View

### 2. Configure Docker Credentials

1. Go to **Manage Jenkins** → **Manage Credentials**
2. Click on **(global)** domain
3. Click **Add Credentials**
4. Fill in:
   - **Kind**: Username with password
   - **Username**: Your Docker Hub username
   - **Password**: Your Docker Hub password or access token
   - **ID**: `docker-user` (must match the Jenkinsfile)
   - **Description**: Docker Hub Credentials

### 3. Configure Tools (Optional)

Go to **Manage Jenkins** → **Global Tool Configuration**

**Maven Configuration**:
- Name: `Maven`
- Install automatically: ✓
- Version: Maven 3.8.6

**JDK Configuration**:
- Name: `JDK-11`
- Install automatically: ✓
- Version: Java 11

### 4. Create Jenkins Pipeline Job

1. Go to Jenkins Dashboard
2. Click **New Item**
3. Enter name: `ScientificCalculator-Pipeline`
4. Select **Pipeline**
5. Click **OK**

### 5. Configure Pipeline

In the pipeline configuration:

**General**:
- ✓ GitHub project
- Project url: `https://github.com/nathanmathewv/ScientificCalculator-DevOps`

**Build Triggers** (optional):
- ✓ GitHub hook trigger for GITScm polling
- ✓ Poll SCM: `H/5 * * * *` (every 5 minutes)

**Pipeline**:
- Definition: **Pipeline script from SCM**
- SCM: **Git**
- Repository URL: `https://github.com/nathanmathewv/ScientificCalculator-DevOps.git`
- Branch: `*/main`
- Script Path: `Jenkinsfile`

Click **Save**

## Pipeline Stages

The Jenkinsfile includes the following stages:

1. **Checkout** - Clone repository
2. **Build Python Application** - Set up Python environment
3. **Test Python Application** - Run Python unit tests
4. **Build Java Application** - Compile Java code with Maven
5. **Test Java Application** - Run JUnit tests
6. **Code Coverage** - Generate JaCoCo coverage report
7. **Package Java Application** - Create executable JAR
8. **Build Docker Images** - Build Docker images for both apps
9. **Push Docker Images** - Push to Docker Hub
10. **Deploy** - Deployment placeholder

## Running the Pipeline

### Manual Trigger
1. Go to your pipeline job
2. Click **Build Now**

### Automatic Trigger
- Pipeline runs automatically on Git push (if webhook configured)
- Or on schedule (if polling enabled)

## Docker Commands

### Build Images Locally
```bash
# Python
cd app
docker build -t scientific-calculator-python:latest .

# Java
cd java-calculator
docker build -t scientific-calculator-java:latest .
```

### Run with Docker Compose
```bash
docker-compose up -d
```

### Push to Docker Hub
```bash
# Login
docker login

# Tag images
docker tag scientific-calculator-python:latest your-dockerhub-username/scientific-calculator-python:latest
docker tag scientific-calculator-java:latest your-dockerhub-username/scientific-calculator-java:latest

# Push
docker push your-dockerhub-username/scientific-calculator-python:latest
docker push your-dockerhub-username/scientific-calculator-java:latest
```

## Email Notifications

To enable email notifications:

1. Go to **Manage Jenkins** → **Configure System**
2. Find **Extended E-mail Notification**
3. Configure SMTP server:
   - SMTP server: `smtp.gmail.com` (for Gmail)
   - Port: `587`
   - Use SSL/TLS: ✓
4. Add credentials for email authentication
5. Update email addresses in Jenkinsfile

## Viewing Reports

After pipeline execution:

### JUnit Test Reports
- Available in build page under **Test Result**

### Code Coverage Report
- Click **JaCoCo Coverage Report** link in build page

### Build Artifacts
- JAR file available under **Build Artifacts**

## Troubleshooting

### Docker Permission Issues
```bash
# Add Jenkins user to docker group
sudo usermod -aG docker jenkins
sudo systemctl restart jenkins
```

### Maven/Java Tool Not Found
- Ensure tools are configured in **Global Tool Configuration**
- Or remove tool configuration from Jenkinsfile and use Docker agents

### Docker Image Push Fails
- Verify Docker Hub credentials ID matches `docker-user`
- Check Docker Hub authentication

### Tests Fail
```bash
# Test locally first
cd app && python test_calculator.py
cd java-calculator && mvn test
```

## Pipeline Customization

### Modify Docker Registry
Change in Jenkinsfile:
```groovy
docker.withRegistry('https://your-registry.com', DOCKER_CREDENTIALS_ID)
```

### Add Deployment Stage
Update the Deploy stage in Jenkinsfile with your deployment commands:
```groovy
stage('Deploy') {
    steps {
        sh 'kubectl apply -f k8s/deployment.yaml'
        // or
        sh 'docker-compose -f docker-compose.prod.yml up -d'
    }
}
```

### Skip Stages
Comment out stages you don't need in the Jenkinsfile.

## Best Practices

1. **Always test locally** before committing
2. **Use semantic versioning** for Docker tags
3. **Keep secrets in Jenkins credentials**, not in code
4. **Review logs** after failed builds
5. **Set up notifications** for build failures

## Support

For issues or questions:
- Check Jenkins console output
- Review Docker logs: `docker logs <container-id>`
- Check GitHub repository issues

## Author

Nathan Mathew V
