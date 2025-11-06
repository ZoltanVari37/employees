pipeline {
    agent {
        docker{   
            image 'eclipse-temurin:17'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages{
        stage('Commit'){
            steps {
                echo "Commit stage"
                script {
                    VERSION_NUMBER = sh(script: './mvnw help:evaluate "-Dexpression=project.version" -q -DforceStdout',returnStdout: true).trim()
                }
                sh './mvnw -B package'
            }
        }

         stage('Acceptence Stage'){
            steps {
                echo "Acceptence Stage"
                sh './mvnw -B integration-test'
            }
        }
    }
}