pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    environment {
        NEXUS_VERSION = "nexus11"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.10:8081"
        NEXUS_REPOSITORY = "maven-kaddem-repository"
        NEXUS_CREDENTIAL_ID = "NexusUserCreds"
    }
    stages {
        stage("Clone code from GitHub") {
            steps {
                script {
                    git branch: 'main', credentialsId: 'NexusUserCreds', url: 'https://github.com/Nihed-A/testDevopsproject.git';
                }
            }
        }
        stage("Maven Build") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }
        stage("Deploying jar to Nexus Repository") {
            steps {
                script {
                 nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: './target/etudiant-1.0.jar']],mavenCoordinate: [artifactId: 'etudiant', groupId: 'tn.esprit.spring.kaddem', packaging: 'jar', version: '1']]]

                }
            }
        }
    }
}