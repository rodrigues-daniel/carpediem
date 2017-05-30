package carpediem

class Mapa {

     String nome
     double latitude
     double longitude
     int indice

    static constraints = {
      nome()
      latitude()
      longitude()
      indice()
    }
}
