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
            stage('SonarQube Analysis'){
                    steps{
                           withSonarQubeEnv('sonarqube') {
                                sh 'mvn clean verify sonar:sonar \
                                 -Dsonar.projectKey=devsecopsprojectkey \
                                 -Dsonar.host.url=http://192.168.33.10:9000 \
                                 -Dsonar.token=sqp_f9e1fa59c665c8c0c57ed391de8245f2e927b662'
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




        stage("Deploying jar to Nexus Repository"){
            steps{
                script{
                     nexusPublisher nexusInstanceId: 'nexus11', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: './target/etudiant-1.0.jar']],mavenCoordinate: [artifactId: 'etudiant', groupId: 'tn.esprit.spring.kaddem', packaging: 'jar', version: '1']]]
                }
            }
        }
}
}

