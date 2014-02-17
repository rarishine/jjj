package com.calp.sampl



import org.junit.*
import grails.test.mixin.*

@TestFor(MeTestingController)
@Mock(MeTesting)
class MeTestingControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/meTesting/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.meTestingInstanceList.size() == 0
        assert model.meTestingInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.meTestingInstance != null
    }

    void testSave() {
        controller.save()

        assert model.meTestingInstance != null
        assert view == '/meTesting/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/meTesting/show/1'
        assert controller.flash.message != null
        assert MeTesting.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/meTesting/list'

        populateValidParams(params)
        def meTesting = new MeTesting(params)

        assert meTesting.save() != null

        params.id = meTesting.id

        def model = controller.show()

        assert model.meTestingInstance == meTesting
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/meTesting/list'

        populateValidParams(params)
        def meTesting = new MeTesting(params)

        assert meTesting.save() != null

        params.id = meTesting.id

        def model = controller.edit()

        assert model.meTestingInstance == meTesting
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/meTesting/list'

        response.reset()

        populateValidParams(params)
        def meTesting = new MeTesting(params)

        assert meTesting.save() != null

        // test invalid parameters in update
        params.id = meTesting.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/meTesting/edit"
        assert model.meTestingInstance != null

        meTesting.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/meTesting/show/$meTesting.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        meTesting.clearErrors()

        populateValidParams(params)
        params.id = meTesting.id
        params.version = -1
        controller.update()

        assert view == "/meTesting/edit"
        assert model.meTestingInstance != null
        assert model.meTestingInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/meTesting/list'

        response.reset()

        populateValidParams(params)
        def meTesting = new MeTesting(params)

        assert meTesting.save() != null
        assert MeTesting.count() == 1

        params.id = meTesting.id

        controller.delete()

        assert MeTesting.count() == 0
        assert MeTesting.get(meTesting.id) == null
        assert response.redirectedUrl == '/meTesting/list'
    }
}
