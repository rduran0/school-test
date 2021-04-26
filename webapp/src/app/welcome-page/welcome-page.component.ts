import { Component } from "@angular/core";

@Component({
  selector: "welcome-page",
  templateUrl: "welcome-page.component.html"
})
export class WelcomePage {
  public welcomeTitle: string

  constructor() {
    this.welcomeTitle = "Welcome"
  }
}
