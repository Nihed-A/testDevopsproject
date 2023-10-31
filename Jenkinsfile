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
           stage('Nexus') {
               steps {
                   withCredentials([usernamePassword(credentialsId: 'NexusUserCreds', usernameVariable: 'Admin', passwordVariable: 'nexus')]) {
                       sh "mvn clean deploy -DskipTests -Dmaven.install.skip=true -DaltDeploymentRepository=deploymentRepo::default::${NEXUS_PROTOCOL}://${NEXUS_URL}/repository/${NEXUS_REPOSITORY}/ -DaltReleaseDeploymentPolicyId=deploymentRepo"
                   }
               }
           }

    }
}