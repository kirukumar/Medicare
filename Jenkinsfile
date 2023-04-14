pipeline{
    agent none
    stages {
        stage('non-parallel Stage'){
            agent {
                label 'master'
            }
            steps{
                echo "This stage will be executed first"
            }
        }
         stage('Run Tests'){
             parallel {
                 stage('Test on Remote'){
                     agent{
                         label 'LinuxSystem'
                     }
                     steps{
                         echo "executing task1"
                     }
                 }
                 stage('Test on Master'){
                     agent{
                         label 'master'
                     }
                     steps{
                         echo "executing task2"
                     }
                 }
             }
                 
           
    }
    }
}
