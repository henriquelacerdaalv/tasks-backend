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
                sleep(10)
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage ('Deploy Backend'){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        stage ('API Test'){
            steps{
                git credentialsId: 'git_login', url: 'https://github.com/henriquelacerdaalv/tasks-test-api'
                sh 'mvn test'
            }
        }
    }
}
