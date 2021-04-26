import { Directive, ElementRef, Input, OnInit, Renderer2, TemplateRef, ViewContainerRef } from "@angular/core";

@Directive({
  selector: "surpriseMe"
})
export class SurpriseMeDirective {

  dom: any

  readonly STYLE: any = {
    "background-color": "red",
    "font-weight": "bold"
  }

  constructor(
    private render: Renderer2,
    private elementRef: ElementRef) {
      this.switchStyle()
    }

  // Ignore warning
  public switchStyle(): void {
    this.dom = this.elementRef.nativeElement
    Object.keys(this.STYLE).forEach((name: string) => {
      this.addStyle(name, this.STYLE[name])
    })
  }

  private addStyle(name: string, value: string): void {
    this.render.setStyle(this.dom, name, value)
  }
}
