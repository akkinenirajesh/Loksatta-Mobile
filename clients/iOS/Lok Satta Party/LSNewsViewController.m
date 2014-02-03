//
//  LSNews.m
//  Lok Satta Party
//
//  Created by Vimukti 22 on 1/26/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import "LSNewsViewController.h"

@implementation LSNewsViewController
@synthesize customImage;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        
        [self.view setBackgroundColor:[UIColor greenColor]];
    }
    return self;
}

-(void)viewDidLoad{
    //need to get data from server
    
    [super viewDidLoad];
    self.title = @"News Feed";
    
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    //no of feeds
    return 1;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    
    static NSString *CellIdentifier = @"Cell";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:CellIdentifier];
    }
    
    
    //this we will get from server data
    NSMutableArray *feeds=[[NSMutableArray alloc]init];
    
    
	LSNewsFeed *currentTweet = [feeds objectAtIndex:indexPath.row];
	
    UIImage	 *feedLogo = [UIImage imageNamed:currentTweet.leader.imageName];
    
	CGRect imageFrame = CGRectMake(2, 8, 40, 40);
	self.customImage = [[UIImageView alloc] initWithFrame:imageFrame];
	self.customImage.image = feedLogo;
	[cell.contentView addSubview:self.customImage];
	
	CGRect contentFrame = CGRectMake(45, 2, 265, 30);
	UILabel *contentLabel = [[UILabel alloc] initWithFrame:contentFrame];
	contentLabel.numberOfLines = 2;
	contentLabel.font = [UIFont boldSystemFontOfSize:12];
	contentLabel.text = [currentTweet content];
	[cell.contentView addSubview:contentLabel];
	
	CGRect dateFrame = CGRectMake(45, 40, 265, 10);
	UILabel *dateLabel = [[UILabel alloc] initWithFrame:dateFrame];
	dateLabel.font = [UIFont systemFontOfSize:10];
	dateLabel.text = [currentTweet dateCreated];
	[cell.contentView addSubview:dateLabel];
	
    
    return cell;
    
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    //row hieght
    return 50;
}

-(void)tableView:(UITableView *)tableView didDeselectRowAtIndexPath:(NSIndexPath *)indexPath
{
    //need to open selected feed.
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end
