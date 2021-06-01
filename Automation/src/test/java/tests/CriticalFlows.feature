Feature: Critical flows
  Description: Test that are related to critical user flows

  #Only local connections are allowed. Test doesn't work
  #Test1
  @Ignored
  Scenario: Test if Sign In form works perfectly
    When user opens Home page "https://zyro.com/"
    And user clicks Sign in button
    And user Sign in with Email "cutestbugs@gmail.com" and Password "Automation99*"

  #Test2
  @Prod
  Scenario Outline: Test if user website is accessible
    When user opens website page "https://cutestbugs.zyrosite.com/"
    And user clicks on "<Menu name>" link
    Examples:
      | Menu name |
      | Blog      |
      | Gallery   |
      | About     |
      | Contact   |
