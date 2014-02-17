package com.calp.sampl

import org.springframework.dao.DataIntegrityViolationException

class MeTestingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [meTestingInstanceList: MeTesting.list(params), meTestingInstanceTotal: MeTesting.count()]
    }

    def create() {
        [meTestingInstance: new MeTesting(params)]
    }

    def save() {
        def meTestingInstance = new MeTesting(params)
        if (!meTestingInstance.save(flush: true)) {
            render(view: "create", model: [meTestingInstance: meTestingInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'meTesting.label', default: 'MeTesting'), meTestingInstance.id])
        redirect(action: "show", id: meTestingInstance.id)
    }

    def show(Long id) {
        def meTestingInstance = MeTesting.get(id)
        if (!meTestingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'meTesting.label', default: 'MeTesting'), id])
            redirect(action: "list")
            return
        }

        [meTestingInstance: meTestingInstance]
    }

    def edit(Long id) {
        def meTestingInstance = MeTesting.get(id)
        if (!meTestingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'meTesting.label', default: 'MeTesting'), id])
            redirect(action: "list")
            return
        }

        [meTestingInstance: meTestingInstance]
    }

    def update(Long id, Long version) {
        def meTestingInstance = MeTesting.get(id)
        if (!meTestingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'meTesting.label', default: 'MeTesting'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (meTestingInstance.version > version) {
                meTestingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'meTesting.label', default: 'MeTesting')] as Object[],
                          "Another user has updated this MeTesting while you were editing")
                render(view: "edit", model: [meTestingInstance: meTestingInstance])
                return
            }
        }

        meTestingInstance.properties = params

        if (!meTestingInstance.save(flush: true)) {
            render(view: "edit", model: [meTestingInstance: meTestingInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'meTesting.label', default: 'MeTesting'), meTestingInstance.id])
        redirect(action: "show", id: meTestingInstance.id)
    }

    def delete(Long id) {
        def meTestingInstance = MeTesting.get(id)
        if (!meTestingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'meTesting.label', default: 'MeTesting'), id])
            redirect(action: "list")
            return
        }

        try {
            meTestingInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'meTesting.label', default: 'MeTesting'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'meTesting.label', default: 'MeTesting'), id])
            redirect(action: "show", id: id)
        }
    }
}
