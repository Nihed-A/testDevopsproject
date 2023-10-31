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
        stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                                        def pom = readMavenPom file: "pom.xml"
                                        def filesByGlob = findFiles(glob: "target/*.${pom.packaging}")
                                        def artifactPath = filesByGlob[0].path

                                        if (fileExists(artifactPath)) {
                                            nexusArtifactUploader(
                                                nexusVersion: NEXUS_VERSION,
                                                protocol: NEXUS_PROTOCOL,
                                                nexusUrl: NEXUS_URL,
                                                groupId: pom.groupId,
                                                version: pom.version,
                                                repository: NEXUS_REPOSITORY,
                                                credentialsId: NEXUS_CREDENTIAL_ID,
                                                artifacts: [
                                                    [artifactId: pom.artifactId, classifier: '', file: artifactPath, type: pom.packaging],
                                                    [artifactId: pom.artifactId, classifier: '', file: "pom.xml", type: "pom"]
                                                ]
                                            )
                                        } else {
                                            error "File not found: ${artifactPath}"
                                        }
                }
            }
        }
    }
}