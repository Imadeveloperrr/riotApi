# riotApi
simple website

frontend -> react

backend -> springboot

frontend 
1. axios 사용
   

backend
1. Spring WebFlux에서 제공하는 Reactor 반응형 라이브러리를 기반 WebClient 사용
2. Spring Framework 5에서 새로 도입된 웹 프레임워크 WebFlux로 Flux와 Mono 타입으로 데이터 스트림 처리

테스트 코드 ( 미완성 )

json 데이터를 내부에 저장한 뒤 api 호출 없이 service 테스트 코드 작성
when 메서드의 인자값에서 퍼블리셔(Mono 타입)가 Null값으로 반환되어 오류 발생 

