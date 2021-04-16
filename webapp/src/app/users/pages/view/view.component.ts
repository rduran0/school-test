import { Component, OnInit } from '@angular/core';
import { timer, Observable, of } from 'rxjs';
import { TestService } from 'src/app/test.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.sass']
})
export class ViewComponent implements OnInit {

  LIMIT = 10;
  MESSAGE = 'Hello world';
  hideContent = true;
  SERVICE_MESSAGE = '';

  constructor(private testService: TestService) { }

  ngOnInit(): void {
    this.testService.getMessage().subscribe(
      value => {
        this.SERVICE_MESSAGE = value;
      }
    );
  }

  toggle() {
    this.hideContent = !this.hideContent;
  }
  
  toggleAsync() {
    timer(500).subscribe(() => {
      this.toggle();
    });
  }
}
