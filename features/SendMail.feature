
Feature: Compose Mail
  

  @tag1
  Scenario: compose mail in Gmail
    Given i am in gmail inbox
    Then i click compose button
    Then i fill to field
    Then i fill subject and body
    Then i attached a file
    Then click on send button
    

 
