# 키친포스

## 요구 사항

### 메뉴 그룹

- 메뉴 그룹을 등록 할 수 있다.
- 메뉴 그룹 리스트를 조회할 수 있다.

### 메뉴

- 메뉴를 등록 할 수 있다.
  - 메뉴의 가격은 존재해야 하고, 음수가 아니어야 한다.
  - 메뉴의 가격은 메뉴에 포함된 메뉴 상품의 총 가격의 합보다 작아야 한다.
- 메뉴 리스트를 조회할 수 있다.

### 상품

- 상품을 등록 할 수 있다.
  - 상품의 가격은 존재해야하고, 음수가 아니어야 한다.
- 상품 리스트를 조회 할 수 있다.

### 주문

- 주문을 받을 수 있다.
  - 주문 시, 주문 항목이 비어있으면 안된다.
  - 주문 시, 주문 항목의 메뉴는 등록된 메뉴여야 한다.
  - 주문 시, 주문 항목의 주문테이블은 등록된 테이블이어야 한다.
  - 주문 시, 비어있는 주문 테이블에서 주문이 되면 안된다.
- 주문 리스트를 조회 할 수 있다.
- 등록된 주문의 상태를 변경 할 수 있다.
  - 완료 상태의 주문의 상태는 변경할 수 없다.

### 주문 테이블

- 주문 테이블을 등록할 수 있다.
- 주문 테이블을 비울 수 있다.
  - 테이블그룹에 속한 주문 테이블은 비울 수 없다.
  - 조리, 식사 상태의 주문 테이블은 비울 수 없다.
- 주문 테이블에 손님을 셋팅 할 수 있다.
  - 방문한 손님 수가 0 명이여도 주문 테이블 셋팅이 가능하다.
  - 방문한 손님 수는 정수여야 한다.
  - 주문 테이블이 빈 상태면 손님을 셋팅 할 수 없다.

### 주문 테이블 그룹

- 테이블 그룹을 등록 할 수 있다.

  - 테이블 그룹의 주문 테이블은 2테이블 이상이어야 한다.
  - 테이블 그룹의 주문 테이블은 이미 등록되어 있어야 한다.
  - 테이블 그룹에 등록하려는 테이블은 비어 있거나, 그룹테이블로 지정되어 있지 않아야 한다.
  - 테이블 그룹에 등록시 등록된 시간을 셋팅 한다.

- 테이블 그룹을 해제 할 수 있다.

  - 조리, 식사 중이 아닌 테이블이라면 해제가 가능하다.

  

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |



