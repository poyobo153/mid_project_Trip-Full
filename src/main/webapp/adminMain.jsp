<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="adminTop.jsp"%>

<div class="btn-controls">
	<div class="btn-box-row row-fluid" style="margin-top: 55px;margin-left: 20px;">
		<a class="btn-box big span4"><i class=" icon-random"></i><b><c:if test="${result == '0' }">결재 정보가 부족합니다.</c:if>
			<c:if test="${result != '0' }">${result }%</c:if></b>
		<p class="text-muted">월간 매출 증감율</p> </a><a 
		class="btn-box big span4"><i class="icon-user"></i><b>${totalToday } 명</b>
		<p class="text-muted">신규 유저</p> </a><a 
		class="btn-box big span4"><i class="icon-money"></i><b>￦<c:if test="${!empty todayPay }"><fmt:formatNumber value="${todayPay }" pattern="#,###"/></c:if>
			<c:if test="${empty todayPay }">0</c:if></b>
		<p class="text-muted">오늘의 수익</p> </a>
	</div>
	<div class="btn-box-row row-fluid" style="margin-left: 20px;">
		<a class="btn-box big span4"><i class=" icon-envelope"></i><b><span style="color: red; font-size: bold">${cnt }</span></b>
		<p class="text-muted">1:1 문의사항</p> </a><a 
		class="btn-box big span4"><i class="icon-group"></i><b>${totalRecordOfAdmin } 명</b>
		<p class="text-muted">총 고객</p> </a><a 
		class="btn-box big span4"><i class="icon-bullhorn"></i><b>0</b>
		<p class="text-muted">신규 공지</p> </a>
	</div>
</div>
<!--/#btn-controls-->
<%@ include file="adminBottom.jsp"%>