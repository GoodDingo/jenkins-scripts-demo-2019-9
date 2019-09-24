#!groovy


USER = 'admin'
JENKINS_MASTER_HOST = env.BUILD_URL.split('/')[2].split(':')[0]


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

    stage('disable jobs') {
      steps {
        script {
          if ("${env.BUILD_ID}" == '1') {
            catchError(buildResult: 'SUCCESS') {
              error('FIRST RUN... loading parameters from git. Loadeded succesfully.... job is now parametrized\n\nRERUN the job with parameters now.\n\n\n')
            }
          }
        }
        sshagent(['vagrant-insecure-ssh']) {
          sh """ 
            cat ScriptConsole/disable-jobs-in-folder.groovy | \
              ssh "${USER}@${JENKINS_MASTER_HOST}" groovy = \
                ${params.FOLDER}
          """
        }
      }
    }
    
  }
}
