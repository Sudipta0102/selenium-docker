pipeline{
    agent{
        label 'node1'
    }

    stages{
        stage('build jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('build image'){
            steps{
                bat "docker build -t=pseudofunc/selindoc1 ."
            }
        }

        stage('push image'){
            steps{
                bat "docker push pseudofunc/selindoc1"
            }
        }
    }
}