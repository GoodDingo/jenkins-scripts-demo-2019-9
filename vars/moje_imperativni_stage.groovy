def call(Closure clos) {

    stage('🔬 Mikroskop') {
        echo "Zacinam se na to koukat"
        clos(this)
        echo "Funguje to" 
    }

}