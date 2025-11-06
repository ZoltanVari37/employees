pipeline {
    environment{
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        SONAR_CREDENTIALS = credentials('sonar-credentials')
        VERSION_NUMBER = sh(script: './mvnw help:evaluate "-Dexpression=project.version" -q -DforceStdout',returnStdout: true).trim()
        IMAGE_NAME = "zoltanvari37/employees:${VERSION_NUMBER}"
    }
    agent {
        dockerfile{   
            filename 'Dockerfile.build'
            args '-e DOCKER_CONFIG=./docker'
        }
    }
    stages{
        stage('Commit'){
            steps {
                echo "Commit stage"
                sh './mvnw -B help:effective-settings > effective-settings.xml'
                CURRENT_FOLDER = sh(script: 'pwd',returnStdout: true).trim()
                M2 = "${CURRENT_FOLDER}/.m2/repository"
                sh "./mvnw -B clean package  -Dmaven.repo.local=${M2}"
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
                sh "docker build -f Dockerfile.layered -t ${IMAGE_NAME} ."
                sh "echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u=$DOCKERHUB_CREDENTIALS_USR --password-stdin"
                sh "docker push ${IMAGE_NAME}"
                sh "docker tag ${IMAGE_NAME} zoltanvari37/employees:latest"
                sh "docker push zoltanvari37/employees:latest"
            }
        }
        stage ('Quality'){
            parallel{
                stage('Sonar'){
                    steps{
                        sh "./mvnw -B sonar:sonar -Dsonar.host.url=http://host.docker.internal:9000 -Dsonar.login=$SONAR_CREDENTIALS_PSW"
                    }
                }
                stage('Dependency Check'){
                     steps {
                        echo 'Dependency Check'
                        //sh "./mvnw dependency-check:check"
                    }
                }
            }
        }
        
    }
}