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
@synthesize mainView;

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.title = @"LSP";
    mainView =[[UIView alloc] initWithFrame:self.view.bounds];
    mainView.backgroundColor=[UIColor whiteColor];
    self.view=mainView;
    
    UIView *buttonsView=[[UIView alloc]init];
    
    UIButton *leaderButton = [[UIButton buttonWithType:UIButtonTypeCustom] initWithFrame:CGRectMake(10, 20, 100, 50)];
    [leaderButton setTitle:@"Leaders" forState:UIControlStateNormal];
    [leaderButton setTitleColor:[UIColor greenColor] forState:UIControlStateNormal];
    [leaderButton addTarget:self action:@selector(loadLeadersView) forControlEvents:UIControlEventTouchUpInside];
    
    UIButton *moreButton =[[UIButton buttonWithType:UIButtonTypeCustom] initWithFrame:CGRectMake(10, 100, 100, 50)];
    [moreButton setTitle:@"Leaders" forState:UIControlStateNormal];
    [moreButton setTitleColor:[UIColor greenColor] forState:UIControlStateNormal];
    [moreButton addTarget:self action:@selector(moreView) forControlEvents:UIControlEventTouchUpInside];
    
    [buttonsView addSubview:leaderButton];
    [buttonsView addSubview:moreButton];
    
    UITableView *newsFeedView=[self loadNewsFeed];
    
    [mainView addSubview:buttonsView];
    [mainView addSubview:newsFeedView];
    
    [self.view addSubview:mainView];
}

-(id) loadNewsFeed{
    UITableView *tableView=[[UITableView alloc]init];
    return tableView;
}

-(void)moreView{
    
}

-(void)loadLeadersView{
    LSLeadersViewController *leadersView = [[LSLeadersViewController alloc]init];
    [self.navigationController pushViewController:leadersView animated:YES];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
