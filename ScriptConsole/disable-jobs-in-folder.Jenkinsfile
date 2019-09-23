// if ("${env.BUILD_ID}") {

//     pipeline {
//         parameters {
//             string name: 'FOLDER', defaultValue: 'TEAMS/KOKOS', description: 'Path to folder that will be log-rotated', trim: true
//         }        
//     }
//     stage("loading parameters") {
//         echo 'Successfully loaded parameters from Git.'
//     }
//     currentBuild.result = 'SUCCESS'
//     return
// }


def getJenkinsMaster() {
    return env.BUILD_URL.split('/')[2].split(':')[0]
}


USER = 'admin'
JENKINS_MASTER_HOST = getJenkinsMaster()


pipeline {
  agent any

  options {
    durabilityHint('PERFORMANCE_OPTIMIZED')
    ansiColor('xterm')
    timestamps()
    buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '1', daysToKeepStr: '60', numToKeepStr: '')
    timeout(5)
    skipStagesAfterUnstable()
  }

  parameters {
    string name: 'FOLDER', defaultValue: 'TEAMS/KOKOS', description: 'Cesta ke slozce kde budou zakazane vsechny joby', trim: true
  }
  
  stages {

    stage('disabling jobs') {
      steps {
        script {
          if ("${env.BUILD_ID}" == '1') {
            error("Parameters loaded successfully.\n\nRERUN WITH PARAMETERS NOW\n\n\n\n")
          }
        }
//        sshagent(['vagrant-insecure-ssh']) {
          echo """ 
            cat ScriptConsole/disable-jobs-in-folder.groovy | \
              ssh "${USER}@${JENKINS_MASTER_HOST}" groovy = \
                ${params.FOLDER}
          """
//        }
      }
    }
  }
}