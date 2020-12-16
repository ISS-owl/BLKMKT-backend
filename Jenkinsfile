def project_url = 'https://github.com/ISS-owl/BLKMKT-backend'
def credentials_Id = '9b65400f-488f-4f79-88e9-1754f2b85c4c'

node {
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
}
