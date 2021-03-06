@Library('ceiba-jenkins-library') _

pipeline {
    //Donde se va a ejecutar el Pipeline
    agent {
        label 'Slave_Induccion'
    }

    //Opciones específicas de Pipeline dentro del Pipeline
    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }

    //Una sección que define las herramientas “preinstaladas” en Jenkins
    tools {
        jdk 'JDK11_Centos' //Verisión preinstalada en la Configuración del Master
    }
/*    Versiones disponibles
      JDK8_Mac
      JDK6_Centos
      JDK7_Centos
      JDK8_Centos
      JDK10_Centos
      JDK11_Centos
      JDK13_Centos
      JDK14_Centos
*/

    //Aquí comienzan los “items” del Pipeline
    stages {
        stage('Checkout') {
            steps {
                echo '------------>Checkout<------------'
                checkout scm
            }
        }

        stage('Compile & Unit Tests') {
            steps {
                echo '------------>Clean Tests<------------'

                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle clean'

                echo '------------>Compile & Unit Tests<------------'
                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle test'
            }
        }

        stage('Static Code Analysis') {
            steps {
                echo '------------>Análisis de código estático<------------'
                sonarqubeMasQualityGatesP(sonarKey:'co.com.ceiba.adn:adnceiba.citasips.alex.benavides',
            sonarName:'"CeibaADN-AdnCeibaCitasIps(alex.benavides)"',
            sonarPathProperties:'./sonar-project.properties')
            }
        }

        stage('Build') {
            steps {
                echo '------------>Build<------------'
                sh './microservicio/gradlew --b ./microservicio/build.gradle build -x test'
            }
        }
    }

    post {
        success {
            echo 'Proceso finalizado correctamente'
            junit 'microservicio/dominio/build/test-results/test/*.xml'
        }
        failure {
            echo 'El proceso ha fallado'
            mail (to: 'alex.benavides@ceiba.com.co', subject: "Failed Pipeline:${currentBuild.fullDisplayName}", body: "Something is wrong with ${env.BUILD_URL}")
        }
    }
}

