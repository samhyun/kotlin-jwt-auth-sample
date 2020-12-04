## 스톡키퍼 기술 과제 project

### table 상세 
**User**
 * id - bigint(20) - pk
 * email - varchar2(255) - 유저 email 주소
 * password - varchar2(255) - 비밀번호
 * first_name - varchar2(255)
 * last_name - varchar2(255)
 * created_at - timestamp
 * modified_at - timestamp
 
 ### package structure
 
 authsample  
  +-- common  
  |   +-- exception              
  +-- config  
  +-- controller  
  |   +-- advice    
  |   +-- payload     
  +-- domain  
  |   +-- repository  
  +-- dto    
  +-- security    
  +-- service    
  


### api

1. /auth/login - POST - 모든 요청에 열려 있음
```json
{
	"email": "hong_1231@test.com",
	"password": "1234"
}
```
* 로그인 API 
* 이메일과 비밀번호 검사 후  JWT 토큰 리턴  
* 잘못된 정보로 로그인 요청시 BadCredentialsException 발생


2. /auth/join - POST - 모든 요청에 열려 있음
```json
{
	"password": "1234",
	"email": "hong_1231@test.com",
	"firstName": "홍",
	"lastName": "길순 "
}
```
* 회원 가입 API 
* 유효성 검사 후에 회원 등록 후 등록된 회원 정보 리턴 
* 이미 등록된 email 로 가입 요청시 UserAlreadyExistsException 발생  
* 비밀번호의 길이가 4보다 작을경우 WeakPasswordException 발생


3. /auth/logout - POST
* 로그아웃 api 
* 구현 안됨

4. /auth/token/refresh - PUT
* 토큰 갱신 api
* 구현 안됨
* 클라이언트에서 만료 시간이 되기전에 토큰을 갱신 하기 위한 기능

5. /auth/token/expiration - GET 
* 토큰 만료시간 조회 API
* 클라이언트에서 발급된 토큰의 만료 시간을 조회하기 위한 기능  

 
API 요청 후 처리과정에서 error 발생시에 CommonAdvice 의 handling 을 통해서 발생한 Exception 의 class 명을 리턴
추후에 개선 필요 



 
  
