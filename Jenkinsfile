pipeline {
    agent any
    environment {
            NEXUS_VERSION = "nexus11"
            NEXUS_PROTOCOL = "http"
            NEXUS_URL = "localhost:8081"
            NEXUS_REPOSITORY = "maven-kaddem-repository"
            NEXUS_CREDENTIAL_ID = "NexusUserCreds"
    }
    stages{
        stage('Compile-package'){
            steps{
                script{
                    sh 'mvn package'
                }
            }
        }
        stage('Sonarqube Analysis'){
            steps{
                script{

                    def mvnHome = tool name: 'maven', type: 'maven'
                    withSonarQubeEnv('sonarqube'){
                        sh "${mvnHome}/bin/mvn verify sonar:sonar"
                    }
                }
            }
        }


        stage("Deploying jar to Nexus Repository"){
            steps{
                script{
                     nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: './target/achat-1.0.jar']],mavenCoordinate: [artifactId: 'achat', groupId: 'tn.esprit.rh', packaging: 'jar', version: '1']]]
                }
            }
        }
}
}

