pipeline{
    agent any
    stages{
        stage ('Build'){
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests'){
            steps {
                sh 'mvn test'
            }
        }
        stage ('Sonar Analysis'){
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL'){
                sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/,**/,src/test/**,**/model/**,**Application.java -Dsonar.login=582a0db693789f478adbaf6b6b19f78d432abe53"
                }
            }
        }
        stage ('Quality Gate'){
            steps{
                sleep(30)
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
