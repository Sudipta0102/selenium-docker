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
            environment{
                            DOCKER = credentials('docker')
            }
            steps{
                bat "docker login -u ${DOCKER_USR} -p ${DOCKER_PSW}"
                bat "docker push pseudofunc/selindoc1"
            }
        }
    }
    post{
        always{
            bat "docker logout"
        }
    }
}