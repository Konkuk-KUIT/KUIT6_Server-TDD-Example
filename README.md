## Java TDD 실습: 은행 계좌
이 프로젝트는 테스트 주도 개발(TDD)의 **Red-Green-Refactor** 사이클을 통해 은행 계좌 기능을 구현하는 간단한 예제입니다.

## TDD 개발 과정
`AccountTest.java`의 테스트 케이스를 중심으로 TDD의 흐름을 확인할 수 있습니다.

### RED: 실패하는 테스트 작성
먼저 요구사항을 검증하는 테스트 코드를 작성합니다. 이 단계에서는 실제 기능 구현 코드가 없으므로 테스트는 실패해야 합니다.

예를 들어, "잔액보다 많은 금액을 출금하면 예외가 발생한다"는 요구사항을 테스트로 먼저 정의합니다.

```java
@Test
@DisplayName("잔액보다 많은 금액을 출금하면 예외가 발생한다.")
void withdraw_throw_exception () throws Exception {
    //given
    Account account = Account.createWithBalance(NaturalNumber.from(10000));

    //when & then
    assertThatThrownBy(() -> account.withdraw(NaturalNumber.from(12000)))
            .isInstanceOf(IllegalArgumentException.class);
 }
```

### GREEN: 테스트를 통과하는 최소한의 코드 작성
이제 방금 작성한 테스트를 통과시킬 수 있는 가장 간단한 코드를 작성합니다.

```java
public Balance sub(NaturalNumber i) {
    if(this.balance < i.getNumber()) {
        throw new IllegalArgumentException(); // <-- 이 코드로 테스트를 통과시킴
    }
    return new Balance(this.balance - i.getNumber());
}
```

### REFACTOR: 코드 개선
테스트가 통과하는 것을 확인한 후, 코드의 구조를 개선합니다. 예를 들어 음수 입력을 막기 위해 `NaturalNumber`라는 별도의 객체를 만들어 도메인 규칙을 강화했습니다. 이처럼 중복을 제거하거나 객체의 책임을 명확히 분리하는 작업을 진행합니다.

```java
private NaturalNumber(int number) {
    if(number < 0){
        throw new IllegalArgumentException(); // 음수 방지 로직을 캡슐화
    }
    this.number = number;
}
```
