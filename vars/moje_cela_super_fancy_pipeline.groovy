def call(Map params) 
{   
    hlaska = params?.hlaska ?: 'funguje'
    pipeline {
        agent any

        stages {
            stage("🍺 Check") { 
                steps { 
                    echo "Checking... " 
                }
            }
            
            stage('😲 QA') {
                parallel {
                    stage('🔬 Unit Tests') {
                        steps { sh 'sleep 3'; echo hlaska }
                    }

                    stage('ℹ️ Generate ALC documents') {
                        steps { sh 'sleep 4'; echo hlaska }
                    }

                    stage('🐷 SonarQube analysis') {
                        steps { sh 'sleep 5'; echo hlaska }
                    }
                }
            }

            stage('🏗️ Deploys') {
                parallel {
                    stage('💻 On Premise') {
                        stages {
                            stage('📦 Build package') {
                                steps { sh 'sleep 3'; echo hlaska }
                            }
                            stage('🏗️ Deploy on-premise') {
                                steps { sh 'sleep 4'; echo hlaska }
                            }
                        }
                    }

                    stage('☁️ AWS') {
                        stages {
                            stage('📦 Build AMI') {
                                steps { sh 'sleep 1'; echo hlaska }
                            }
                            stage('🏗️ Deploy to AWS') {
                                steps { sh 'sleep 1'; echo hlaska }
                            }
                        }
                    }
                }
            }
        }
    }

}