import { HttpErrorResponse } from "@angular/common/http";
import { Component } from "@angular/core";
import { SupportedStatus } from "../constant/http.constant";
import { ErrorsService } from "../service/errors.service";

@Component({
  selector: "error-page",
  templateUrl: "error-page.component.html"
})
export class ErrorPage {

  constructor(private errorService: ErrorsService) {}

  public triggerError(status: SupportedStatus): void {
    this.errorService.testError(status)
      .subscribe(response => {},
        this.handleCustomError)
  }

  public triggerBadRequest(): void {
    this.errorService.test400()
      .subscribe(response => {})
  }

  private handleCustomError(error: HttpErrorResponse): void {
    alert(
      `   Http Error
      Status code: ${error.status}
      Status name: ${error.statusText} `
    )
  }
}
