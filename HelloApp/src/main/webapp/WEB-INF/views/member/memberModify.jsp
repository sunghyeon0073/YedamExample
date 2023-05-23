<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3>회원정보수정 페이지.(memberModify.jsp)</h3>

<form action="modifyMember.do" method="post">
	<table class="table">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="email"
				value="${memberInfo.email }" readonly></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="password"
				value="${memberInfo.password }"></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><input type="text" name="phone"
				value="${memberInfo.phone }"></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="address"
				value="${memberInfo.address }"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">저장(수정)</button>
			</td>
		</tr>
	</table>
</form>
<script>
	
</script>