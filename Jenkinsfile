pipeline {
    environment{
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        VERSION_NUMBER = sh(script: './mvnw help:evaluate "-Dexpression=project.version" -q -DforceStdout',returnStdout: true).trim()
        IMAGE_NAME = "zoltanvari/employees:${VERSION_NUMBER}"
    }
    agent {
        dockerfile{   
            filename 'Dockerfile.build'
            args '-e DCOKER_CONFIG=./docker'
        }
    }
    stages{
        stage('Commit'){
            steps {
                echo "Commit stage"
                
                sh './mvnw -B clean package'
            }
        }

        stage('Acceptence Stage'){
            steps {
                echo "Acceptence Stage"
                sh './mvnw -B integration-test'
            }
        }
        stage('Docker'){
            steps{
                sh "docker build -f DOckerfile.layered -t ${IMAGE_NAME}"
                sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u=${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
                sh "docker push ${IMAGE_NAME}"
                sh "docker tag ${IMAGENAME} zoltanvari/employees:latest"
                sh "docker push zoltanvari/employees:latest"
            }
        }
    }
}