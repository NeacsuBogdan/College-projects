import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipecardcontentComponent } from './recipecardcontent.component';

describe('RecipecardcontentComponent', () => {
  let component: RecipecardcontentComponent;
  let fixture: ComponentFixture<RecipecardcontentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipecardcontentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecipecardcontentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
