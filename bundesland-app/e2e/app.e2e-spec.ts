import { BundeslandAppPage } from './app.po';

describe('bundesland-app App', () => {
  let page: BundeslandAppPage;

  beforeEach(() => {
    page = new BundeslandAppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
