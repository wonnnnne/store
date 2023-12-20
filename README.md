# store
Spring Boot - 쇼핑몰 프로젝트

# 환경
JAVA 11 / Spring Boot 2.7.16 / Mysql

# API
- 회원가입
- 로그인
- 상품리스트

# 기본정책
- 회원가입 시에 회원고유의 장바구니도 같이생성
- 상품 장바구니에 모두 넣고 주문성공하면 상품에서 재고관리
- 재고없으면 장바구니담기 불가능, 장바구니에 담겨져 있어도 주문도 불가능
- 상품 주문 status값 하면 ORDERED 주문취소하면 CANCELED로 업데이트
- 파라미터값이 잘못된경우는  IllegalArgumentException
- 재고가 없는경우는  InsufficientStockException
