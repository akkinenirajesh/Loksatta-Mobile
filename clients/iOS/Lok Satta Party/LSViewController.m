//
//  LSViewController.m
//  Lok Satta Party
//
//  Created by Vimukti 22 on 1/26/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import "LSViewController.h"

@interface LSViewController ()

@end

@implementation LSViewController
@synthesize tabBarController;

- (void)viewDidLoad
{
    [super viewDidLoad];
//    [self.view setBackgroundColor:[UIColor greenColor]];
	// Do any additional setup after loading the view, typically from a nib.
    
    tabBarController = [[UITabBarController alloc]init];
    tabBarController.delegate=self;
    
    LSNewsViewController *newsView = [[LSNewsViewController alloc]init];
    UINavigationController *nav1=[[UINavigationController alloc]initWithRootViewController:newsView];
    nav1.navigationBar.barStyle=UIBarStyleBlackOpaque;
    nav1.title = @"News";
    
    LSLeadersViewController *leadersView = [[LSLeadersViewController alloc] init];
    UINavigationController *nav2=[[UINavigationController alloc]initWithRootViewController:leadersView];
    nav2.navigationBar.barStyle=UIBarStyleBlackTranslucent;
    nav2.title=@"Leaders";
    
    LSDonateViewController *donateView = [[LSDonateViewController alloc]init];
    UINavigationController *nav3=[[UINavigationController alloc]initWithRootViewController:donateView];
    nav3.navigationBar.barStyle=UIBarStyleBlack;
    nav3.title=@"Donate";
    
    LSVolunteerViewController *volunteerView = [[LSVolunteerViewController alloc]init];
    UINavigationController *nav4=[[UINavigationController alloc]initWithRootViewController:volunteerView];
    nav4.navigationBar.barStyle=UIBarStyleDefault;
    nav4.title=@"Volunteer";
    
    tabBarController.viewControllers=[NSArray arrayWithObjects:nav1,nav2,nav3,nav4, nil];
    
    [self.view addSubview:tabBarController.view];
    
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
