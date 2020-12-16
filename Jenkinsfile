pipeline {
    agent {
        node {
            label 'master'
        }
    }

    environment {
        // Github
        PROJECT_URL = 'https://github.com/ISS-owl/BLKMKT-backend'
        GITHUB_ACCOUNT = 'Sh-Zh-7'
        GITHUB_CREDENTIALS_ID = '9b65400f-488f-4f79-88e9-1754f2b85c4c'
        // Sonarcube
        SONAR_CREDENTIAL_ID= 'sonar-token'
        BRANCH_NAME = 'main'
    }

    stages {
        stage('pull code') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    submoduleCfg: [],
                    userRemoteConfigs: [
                        [
                            credentialsId: "${GITHUB_CREDENTIALS_ID}",
                            url: "${PROJECT_URL}"
                        ]
                    ]
                ])
            }
        }

        stage('compile project') {
            steps {
                sh 'echo compile project'
                sh 'mvn clean install -Dmaven.test.skip=true -gs `pwd`/mvn_settings.xml'
            }
        }

        stage('sonarqube analysis') {
            steps {
                 withCredentials([string(credentialsId: "$SONAR_CREDENTIAL_ID", variable: 'SONAR_TOKEN')]) {
                    withSonarQubeEnv('sonar') {
                            sh "mvn sonar:sonar -o -gs `pwd`/mvn_settings.xml -Dsonar.branch=$BRANCH_NAME -Dsonar.login=$SONAR_TOKEN"
                        }
                 }
                 timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                 }
            }
        }
    }
}
