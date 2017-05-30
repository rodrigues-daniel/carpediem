package carpediem

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MapaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def marcadores(){

      render view: 'marcadores'

    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Mapa.list(params), model:[mapaCount: Mapa.count()]
    }

    def show(Mapa mapa) {
        respond mapa
    }

    def create() {
        respond new Mapa(params)
    }

    @Transactional
    def save(Mapa mapa) {
        if (mapa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (mapa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mapa.errors, view:'create'
            return
        }

        mapa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mapa.label', default: 'Mapa'), mapa.id])
                redirect mapa
            }
            '*' { respond mapa, [status: CREATED] }
        }
    }

    def edit(Mapa mapa) {
        respond mapa
    }

    @Transactional
    def update(Mapa mapa) {
        if (mapa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (mapa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mapa.errors, view:'edit'
            return
        }

        mapa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'mapa.label', default: 'Mapa'), mapa.id])
                redirect mapa
            }
            '*'{ respond mapa, [status: OK] }
        }
    }

    @Transactional
    def delete(Mapa mapa) {

        if (mapa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        mapa.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'mapa.label', default: 'Mapa'), mapa.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mapa.label', default: 'Mapa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
