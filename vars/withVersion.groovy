def call(String verze, Closure clos) {

    stage('🔬 Mikroskop') {
        withEnv("MOJE_VERZE=${verze}") {
            echo "... nastavuji verzi"
            clos(this)
            echo "... konec verze" 
        }
    }

}