import { ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';

import { ViewComponent } from './view.component';
import { By } from '@angular/platform-browser';
import { TestService } from 'src/app/test.service';
import { of } from 'rxjs';

let serviceStub: any;

describe('ViewComponent', () => {
  let component: ViewComponent;
  let fixture: ComponentFixture<ViewComponent>;

  serviceStub = {
    getMessage: () => of('Message from service')
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewComponent ],
      providers: [{provide: TestService, useValue: serviceStub}]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // 1
  it('should create component', () => {
    expect(component).toBeTruthy();
  });

  // 2 
  it('should have a limit of 10', () => {
    expect(component.LIMIT).toBe(10);
    expect(component.LIMIT).toBeGreaterThan(5);
  });

  // 3
  it('should have a message with `warn`', () => {
    expect(component.MESSAGE).toContain('world');
    expect(component.MESSAGE).toMatch(/world/);
  });

  // 4
  it('should toggle the variable boolean', () => {
    expect(component.hideContent).toBeTruthy();
    component.toggle();
    expect(component.hideContent).toBeFalsy();
  });

  // 5
  it('should have an h1 tag of `Alert Button`', () => {
    expect(fixture.debugElement.query(By.css('h1')).nativeElement.innerText).toBe('Alert Button');
  });


  // 6
  it('should toggle the message boolean asynchronously', fakeAsync(() => {
    expect(component.hideContent).toBeTruthy();
    component.toggle();
    //tick(1); // fails
    //tick(500);   // passes
    expect(component.hideContent).toBeFalsy();
  }));
});
