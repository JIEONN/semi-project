<%@page import="trendy.review.vo.ReviewComment"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="trendy.review.vo.Review"%>
<%@page import="java.util.ArrayList"%>
<%@page import="trendy.product.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Product p = (Product) request.getAttribute("p");
ArrayList<Review> reviewList = (ArrayList<Review>) request.getAttribute("list");
String pageNavi = (String) request.getAttribute("pageNavi");
int totalReview = (Integer) request.getAttribute("totalReview");

ArrayList<ReviewComment> reviewCommentList = (ArrayList<ReviewComment>) request.getAttribute("reviewCommentList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
<style>
.page-content {
	width: 1200px;
	margin: 0 auto;
	padding-bottom: 30px;
}

.product-info {
	overflow: hidden;
	margin-top: 100px;
	padding-bottom: 100px;
	border-bottom: 1px solid #ccc;
}

.btns {
	width: 60px;
	height: 20px;
	line-height: 20px;
	border: 1px solid #ccc;
	background-color: #fff8f0;
	border-radius: 2px;
	font-size: 13px;
	padding: 2px;
	margin: 2px;
	text-decoration: none;
	color: black;
	float: right;
	text-align: center;
}

.reply {
	font-size: 15px;
	margin: 4px;
	text-decoration: none;
	color: #8C8B88;
	float: right;
	text-align: center;
	font-size: 14px;
}

.input-btn {
	float: right;
}

.info-left {
	width: 60%;
	height: 100%;
	float: left;
	text-align: center;
}

.info-right {
	width: 40%;
	float: left;
}

#img {
	width: 550px;
	height: 750px;
}

.tble {
	margin: 0;
	width: 100%;
	border-collapse: collapse;
	border: 1px solid transparent;
}

.tble tr {
	height: 80px;
}

.tble td {
	width: 150px;
}

.color {
	width: 30px;
	height: 30px;
	background-color: black;
}

.ul-count {
	list-style-type: none;
	overflow: hidden;
	padding: 0;
	margin: 0 auto;
}

.ul-count>li {
	float: left;
	padding: 5px;
	height: 100px;
	line-height: 100px;
}

.ul-count>li:first-child {
	text-align: left;
	width: 200px;
	padding-right: 20px;
	font-weight: bolder;
}

.combo {
	width: 20px;
	height: 20px;
}

#up:hover, #down:hover {
	cursor: pointer;
}

.ul-count input {
	width: 20px;
	height: 20px;
}

.tble tr:last-child, .tble tr:nth-last-child(2) {
	text-align: center;
	border-top: 1px solid #ccc;
}

.tble tr:nth-last-child(3) {
	height: 150px;
}

.tble tr:first-child {
	font-weight: bolder;
	font-size: 1.5em;
}

.buy {
	height: 60px;
	line-height: 60px;
	border-right: 1px solid #ccc;
}

.buy:hover, .cart:hover {
	cursor: pointer;
	font-weight: bolder;
}

.product-notice {
	padding: 100px;
}

.notice-tbl {
	margin: 0 auto;
	width: 70%;
	border-collapse: collapse;
	border: 1px solid transparent;
}

.notice-tbl tr {
	border-bottom: 1px solid #ccc;
}

.notice-tbl td {
	font-size: 12px;
	padding: 10px;
}

.notice-tbl tr:first-child {
	color: gray;
	font-weigh: bolder;
}

.grade-info {
	margin: 100px auto;
	width: 85%;
}

.grade-tbl {
	width: 100%;
	margin-top: 50px;
	margin-bottom: 50px;
	border-collapse: collapse;
	border: 1px solid transparent;
}

.grade-tbl tr {
	border-bottom: 1px solid #ccc;
}

.grade-tbl td {
	padding: 10px;
	padding-right: 30px;
}

.grade-tbl tr>td:first-child {
	font-size: 20px;
	font-weight: bolder;
}

.grade-tbl tr:first-child {
	font-weight: bolder;
	border-top: 5px double black;
}

.grade-tbl tr>td:nth-child(2), .grade-tbl tr>td:nth-child(3) {
	text-align: center;
}

.product-review {
	padding: 100px;
	margin: 0 auto;
}

.review-tbl {
	width: 1000px;
	border: 1px solid transparent;
	border-collapse: collapse;
	margin-bottom: 15px;
}

.review-tbl tr {
	border-bottom: 1px solid #ccc;
}

.review-tbl td {
	padding: 10px;
}

.review-tbl td:first-child {
	width: 80%;
}

.review-tbl td:last-child {
	width: 20%;
}

.review-right {
	padding: 20px;
	border-left: 1px solid #ccc;
	font-size: 14px;
}

.review-left {
	margin: 10px;
}

.review-count {
	border-bottom: 2px double black;
}

.reviewImg {
	width: 150px;
	height: 150px;
	maring: 50px;
}

.reviewWrite {
	float: right;
}

.pagination {
	list-style-type: none;
	padding: 0;
	overflow: hidden;
	text-align: center;
	display: flex;
	justify-content: center;
}

.page-each {
	border-radius: 50px;
}

.pagination .page-each {
	text-decoration: none;
	display: block;
	height: 30px;
	line-height: 30px;
	width: 30px;
	color: black;
}

.pagination>li:hover {
	font-size: 1.2em;
}

.active-page {
	background-color: orange;
	color: white;
}

.reviewWriteBox {
	display: none;
}

.ul-reviewWrite {
	list-style-type: none;
	padding: 0;
	margin: 0 auto;
}

.reviewContent, .reviewCommentContent {
	resize: none;
}

.reviewComment-list {
	overflow: hidden;
	font-size: 15px;
	color: #8C8C8C;
}

.reply-box {
	display: none;
}

.reviewComment-list>* {
	float: left;
	padding: 2px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<div class="page-content">
		<div class="product-info">
			<div class="info-left">
				<img src="/upload/product/<%=p.getProductFilepath()%>" id="img">
			</div>
			<div class="info-right">
				<table border="1" class="tble">
					<tr style="border-bottom:1px solid #ccc">
						<td colspan="2"><%=p.getProductName()%> (<%=p.getProductId()%>)</td>
					</tr>
					<tr>
						<td>PRICE</td>
						<td><input type="hidden" class="price"
							value="<%=p.getProductPrice()%>"> <%
 DecimalFormat df = new DecimalFormat("#,###");
 String price = df.format(p.getProductPrice());
 %> <%=price%>원</td>
					</tr>
					<tr>
						<td>COLOR</td>
						<td><div class="color"></div></td>
					</tr>
					<tr>
						<td>SIZE</td>
						<td>F</td>
					</tr>
					<tr></tr>
					<tr>
						<td colspan="2">
							<ul class="ul-count">
								<li><%=p.getProductName()%></li>
								<li><input type="text" name="count" id="count"
									class="count" value="1" readonly></li>
								<li>
									<div class="combo">
										<span class="material-icons" id="up">expand_less</span>
									</div>
									<div class="combo">
										<span class="material-icons" id="down">expand_more</span>
									</div>
								</li>
								<li><span class="totalPrice"> <%=price%>
								</span> (<span class="totalCount">1</span>개)</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td><div class="buy" onclick="buyItNow('<%=p.getProductId()%>');">BUY IT NOW</div></td>
                        <td><div class="cart" onclick="addToCart('<%=p.getProductId()%>');">ADD TO CART</div></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="grade-info">
			<h2>회원혜택</h2>
			<p>총 누적금액을 반영하여 등급자격 충족 시 자동으로 회원등급이 변경됩니다.</p>
			<p>향후 멤버쉽 단계별 혜 및 기준은 변경될 수 있습니다.</p>
			<table border="1" class="grade-tbl">
				<tr>
					<th>회원등급</th>
					<td>누적 구매금액</td>
					<td>등급혜택</td>
				</tr>
				<tr>
					<th>VIP</th>
					<td>1,000만원 이상</td>
					<td>무조건 무료배송. 기본적립10%</td>
				</tr>
				<tr>
					<th>GOLD</th>
					<td>500만원 이상 1,000만원 미만</td>
					<td>무조건 무료배송. 기본적립5%</td>
				</tr>
				<tr>
					<th>SILVER</th>
					<td>50만원 이상 500만원 미만</td>
					<td>기본적립2%</td>
				</tr>
				<tr>
					<th>BRONZE</th>
					<td>신규가입. 50만원 미만</td>
					<td>기본적립1%</td>
				</tr>
			</table>
			<h3>멤버쉽 및 쿠폰안내</h3>
			<p>- 회원등급은 총 구매누적금액 기준으로 선정됩니다.</p>
			<p>- 회원등급조정은 취소,환불을 포함한 배송완료 주문건 기준 구매 실제금액 측정 후 책정됩니다.</p>
			<p>- 등급조정은 교환, 환불 예상기간을 고려하여 배송완료 후 7-10일 이후 조정됩니다.</p>
			<p>- 주문취소 및 환불 시 구매실적은 제외됩니다.</p>


		</div>
		<div class="product-detail">
			<%=p.getProductDetailBr()%>
		</div>
		<div class="product-notice">
			<table border="1" class="notice-tbl">
				<tr>
					<td colspan="2">Notification 상품정보제공고시
				</tr>
				<tr>
					<td>제품소개</td>
					<td>상품설명 참고</td>
				</tr>
				<tr>
					<td>색상</td>
					<td>상품설명 참고</td>
				</tr>
				<tr>
					<td>치수</td>
					<td>상품설명 참고</td>
				</tr>
				<tr>
					<td>제조자/수입자</td>
					<td>트랜디/거래처(보안사항)</td>
				</tr>
				<tr>
					<td>제조국</td>
					<td>국내공급</td>
				</tr>
				<tr>
					<td>세탁방법 및 취급주의사항</td>
					<td>모든상품 첫세탁은 드라이크리닝을 권장하며 워싱팁 참고</td>
				</tr>
				<tr>
					<td>제조년월</td>
					<td>주문일로부터 3일 이내</td>
				</tr>
				<tr>
					<td>품질보증기준</td>
					<td>본 제품의 품질보증기간은 제품수령일로부터 1주일 이내 교환 및 환불이 가능합니다.</td>
				</tr>
				<tr>
					<td>A/S관련 전화번호</td>
					<td>02-123-3333</td>
				</tr>
				<tr>
					<td>주문 후 예상 배송기간</td>
					<td>평균 1-5일(상품종류에 따라서 배송기간이 다소 차이가 있습니다.)</td>
				</tr>
			</table>
		</div>
		<div class="product-review">
			<div class="review-count">
				REVIEW(<%=totalReview%>)
			</div>
			<table border="1" class="review-tbl">

				<%
				for (Review r : reviewList) {
				%>
				<tr>
					<td>
						<div class="review-left">
							<p>
								<strong><%=r.getMemberId()%></strong> 님의 리뷰입니다.
							</p>
							<p><%=r.getReviewContentBr()%></p>
							<textarea cols="90" rows="10" name="newContent"
								class="reviewContent" style="display: none; min-height: 90px;"><%=r.getReviewContentBr()%></textarea>

							<%
							if (r.getFilepath() != null) {
							%>
							<img src="/upload/review/<%=r.getFilepath()%>" class="reviewImg">
							<%
							}
							%>
							<input type="file" name="newfile" class="newfile"
								style="display: none;">

						</div> <!-- 후기 작성자인 경우만 수정삭제 --> <%
 if (m != null) {
 %> <%
 if (m.getMemberId().equals(r.getMemberId())) {
 %> <a href="javascript:void(0)" class="btns"
						onclick="deleteReview(this,'<%=r.getReviewNo()%>','<%=r.getProductId()%>' ,'<%=r.getFilepath()%>')">삭제</a>
						<a href="javascript:void(0)" class="btns"
						onclick="modifyReview(this,'<%=r.getReviewNo()%>','<%=r.getProductId()%>' , '<%=r.getFilepath()%>')">수정</a>
						<%
						}
						%> <%
 }
 %> <!-- 답글 리스트 출력 --> <%
 for (ReviewComment rc : reviewCommentList) {
 %> <!-- 해당 댓글의 답글인 경우만 출력 --> <%
 if (r.getReviewNo() == rc.getReviewNo()) {
 %>
						<div class="reviewComment-list">
							<p>
								<span class="material-icons">subdirectory_arrow_right</span>
							</p>
							<div>
								<p><%=rc.getMemberId()%>님의 답글입니다. (<%=rc.getReviewReDate()%>)
								</p>
								<p class="reviewCommentList"><%=rc.getReviewReContentBr()%></p>
							</div>
						</div> <%
 }
 %> <%
 } //답글 리스트 출력용 for문 종료
 %> <%
 if (m != null) {
 %> <%
 if (m.getAdmin() == 0) {
 %> <!-- 관리자만  답글기능 넣어주기 --> <a href="javascript:void(0)" class="reply">답글작성</a>
						<div class="reply-box">
							<form action="insertReviewComment.do" method="get">
								<input type="hidden" name="memberId"
									value="<%=m.getMemberId()%>"> <input type="hidden"
									name="reviewNo" value="<%=r.getReviewNo()%>"> <input
									type="hidden" name="productId" value="<%=p.getProductId()%>">
								<textarea cols="93" rows="5" name="reviewCommentContent"
									class="reviewCommentContent"></textarea>
								<input type="submit" value="작성완료">
							</form>
						</div> <%
 }
 %> <%
 }
 %>

					</td>
					<td>
						<div class="review-right">
							작성일
							<%=r.getReviewDate()%><br>
						</div>
					</td>
				</tr>
				<%
				}
				%>
			</table>
			<!--리뷰작성 -->
			<%
			if (m != null) {
			%>
			<a href="javascript:void(0)" class="rwShow btns">리뷰작성</a>
			<div class="reviewWriteBox">
				<form action="/insertReviewFrm.do" method="post"
					enctype="multipart/form-data">
					<ul class="ul-reviewWrite">
						<li><input type="hidden" name="memberId"
							value="<%=m.getMemberId()%>"> <input type="hidden"
							name="productId" value="<%=p.getProductId()%>"></li>
						<li><textarea cols="120" rows="10" name="reviewContent"
								class="reviewContent"></textarea></li>
						<li><input type="file" name="file" class="file" value="1">
							<input type="submit" value="작성완료" class="input-btn"></li>
					</ul>

				</form>
			</div>
			<%
			}
			%>
			<div class="page-navi">
				<%
				if (reviewList.size() != 0) {
				%>
				<%=pageNavi%>
				<%
				}
				%>
			</div>

		</div>
		<div class="product-qna"></div>
	</div>
	<script>
		//수정
		function modifyReview(obj,reviewNo,productId,filepath){
			if(filepath == "null"){
				$(obj).prev().prev().children().eq(1).hide();
				$(obj).prev().prev().children().eq(2).show();
				$(obj).prev().prev().children().eq(3).show();
				$(obj).text("수정완료");
				$(obj).prev().text("수정취소");
				$(obj).attr("onclick","modifyComplete(this,'"+reviewNo+"','"+productId+"','"+filepath+"')");
				$(obj).prev().attr("onclick","modifyCancel(this,'"+reviewNo+"','"+productId+"','"+filepath+"')");
			}else{
				$(obj).prev().prev().children().eq(1).hide();
				$(obj).prev().prev().children().eq(3).hide();
				$(obj).prev().prev().children().eq(2).show();
				$(obj).prev().prev().children().eq(4).show();
				$(obj).text("수정완료");
				$(obj).prev().text("수정취소");
				$(obj).attr("onclick","modifyComplete(this,'"+reviewNo+"','"+productId+"','"+filepath+"')");
				$(obj).prev().attr("onclick","modifyCancel(this,'"+reviewNo+"','"+productId+"','"+filepath+"')");
			}
		}
		//수정취소
		function modifyCancel(obj,reviewNo,productId,filepath){
			if(filepath == "null"){
				$(obj).prev().children().eq(1).show();
				$(obj).prev().children().eq(2).hide();
				$(obj).prev().children().eq(3).hide();
				$(obj).next().text("수정");
				$(obj).text("삭제");
				$(obj).next().attr("onclick","modifyReview(this,'"+reviewNo+"','"+productId+"','"+filepath+"')");
				$(obj).attr("onclick","deleteReview(this,'"+reviewNo+"','"+productId+"','"+filepath+"')");
			}else{
				$(obj).prev().children().eq(1).show();
				$(obj).prev().children().eq(3).show();
				$(obj).prev().children().eq(2).hide();
				$(obj).prev().children().eq(4).hide();
				$(obj).next().text("수정");
				$(obj).text("삭제");
				$(obj).next().attr("onclick","modifyReview(this,'"+reviewNo+"','"+productId+"','"+filepath+"')");
				$(obj).attr("onclick","deleteReview(this,'"+reviewNo+"','"+productId+"','"+filepath+"')");
			}
		}
		//수정완료
		function modifyComplete(obj, reviewNo, productId, filepath){
			if(filepath == "null"){
				const form = $("<form action='/updateReview.do' method='post' enctype='multipart/form-data'></form>");
				form.append($(" <input type='text' name='reviewNo' value='"+reviewNo+"'>"));
				form.append($(" <input type='text' name='productId' value='"+productId+"'>"));
				form.append($(obj).prev().prev().children().eq(2));
				form.append($(obj).prev().prev().children().eq(2));
			
				$("body").append(form);
				form.submit();
			}else{
				const form = $("<form action='/updateReview.do' method='post' enctype='multipart/form-data'></form>");
				form.append($(" <input type='text' name='reviewNo' value='"+reviewNo+"'>"));
				form.append($(" <input type='text' name='productId' value='"+productId+"'>"));
				form.append($(obj).prev().prev().children().eq(2));
				form.append($(obj).prev().prev().children().eq(3));

				$("body").append(form);
				form.submit();
			}
		}
		
		//삭제
		
		function deleteReview(obj,reviewNo, productId, filepath){
			if(confirm("댓글을 삭제하겠습니까?")){
				location.href="/deleteReview.do?reviewNo="+reviewNo+"&productId="+productId;
			}
		}
		
		
		
		//리뷰작성버튼
		$(".rwShow").on("click",function(){
			if($(this).text() == "리뷰작성"){
				$(this).text("작성취소");
			}else{
				$(this).text("리뷰작성");
			}
			$(".reviewWriteBox").toggle();
			$(".reviewContent").focus();
		});
	
		//리뷰댓글
		$(".reply").on("click",function(){
			if($(this).text() == "답글작성"){
				$(this).text("작성취소");
			}else{
				$(this).text("답글작성");
			}
			$(this).next().toggle();
		});
		
		//구매하기
        function buyItNow(productId){
            const memberId = $("[name=memberId]").val();
            const count = $(".count").val();
            if(memberId == ""){
                alert("로그인이 필요합니다.");
            }else{
                location.href="/clientPaymentFrm.do?productId="+productId+"&amount="+count;
            }
        }
        //장바구니
        function addToCart(productId){
            const count = $(".count").val();
            const memberId = $("[name=memberId]").val();
            if(memberId == ""){
                alert("로그인이 필요합니다.");
            }else{
            	location.href="/insertCart.do?memberId="+memberId+"&productId="+productId+"&count="+count;
            }
        }
		
		
			
		
		
		//updown버튼
		$("#up").on("click",function(){
			const count = Number($(".count").val());
			const countUp = count+1;
			$(".count").attr("value",countUp);
			$(".totalCount").text(countUp);
			//총 금액
			const price = Number($(".price").val());
			const totalPrice = countUp * price;
			$(".totalPrice").text(totalPrice.toLocaleString('ko-KR'));
			
			
		});
		$("#down").on("click",function(){
			const count = $(".count").val();
			if(count != 1){
				const countDown = Number(count)-1;
				$(".count").attr("value",countDown);
				$(".totalCount").text(countDown);
				//총 금액
				const price = Number($(".price").val());
				const totalPrice = countDown * price;
				$(".totalPrice").text(totalPrice.toLocaleString('ko-KR'));
			}
		});
	
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>
