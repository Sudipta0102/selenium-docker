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
                            DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                bat 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                bat "docker push pseudofunc/selindoc1"
            }
        }
    }
}