<%@ page import="com.calp.sampl.MeTesting" %>



<div class="fieldcontain ${hasErrors(bean: meTestingInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="meTesting.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${meTestingInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: meTestingInstance, field: 'job', 'error')} required">
	<label for="job">
		<g:message code="meTesting.job.label" default="Job" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="job" required="" value="${meTestingInstance?.job}"/>
</div>

