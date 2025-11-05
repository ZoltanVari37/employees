pipeline {
    agent {
        docker{   
            image 'eclipse-temurin:17'
        }
    }
    stages{
        stage('Commit'){
            steps {
                echo "Commit stage"
                sh './mvnw -B package'
            }
        }
    }
}