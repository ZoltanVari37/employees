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
                sh './mvnw -B package'
            }
        }
    }
}