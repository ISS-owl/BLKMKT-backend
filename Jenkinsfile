def project_url = 'https://github.com/ISS-owl/BLKMKT-backend'
def credentials_Id = '9b65400f-488f-4f79-88e9-1754f2b85c4c'

node {
    environment {
        GITHUB_ACCOUNT = 'Sh-Zh-7'
        BRANCH_NAME = 'main'
    }

    stage('pull code') {
        checkout([
            $class: 'GitSCM',
            branches: [[name: '*/main']],
            doGenerateSubmoduleConfigurations: false,
            extensions: [],
            submoduleCfg: [],
            userRemoteConfigs: [
                [
                    credentialsId: "${credentials_Id}",
                    url: "${project_url}"
                ]
            ]
        ])
    }

    stage('compile project') {
        sh 'echo compile project'
        container ('maven') {
            sh 'mvn clean install -Dmaven.test.skip=true -gs `pwd`/mvn_settings.xml'
        }
    }

    stage('sonarqube analysis') {
        steps {
            container ('maven') {
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
