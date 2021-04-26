import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { ErrorPage } from "./error-page.component";

@NgModule({
  declarations: [ErrorPage],
  imports: [
    HttpClientModule
  ],
  exports: [ErrorPage]
})
export class ErrorModule {}
