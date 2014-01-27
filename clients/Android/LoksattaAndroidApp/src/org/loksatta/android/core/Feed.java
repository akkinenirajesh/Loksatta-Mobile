package org.loksatta.android.core;

/**
 * 
 */
public class Feed extends AbstractCore {

	/**
	 * Name of the Author of this feed if any
	 */
	private String authorName;

	/**
	 * Identity of the Author of this feed if any
	 */
	private String authorId;

	/**
	 * Title of the Feed
	 */
	private String title;

	/**
	 * Summary of the Feed
	 */
	private String summary;

	/**
	 * 
	 */
	private String content;

	/**
	 * Facebook Id
	 */
	private String facebookId;

	/**
	 * Google Plus Id
	 */
	private String gPlusId;

	/**
	 * Id of the Twitter
	 */
	private String twitterId;

	/**
	 * No. Of Likes
	 */
	private long likesCount;

	/**
	 * No. Of Shares
	 */
	private long sharesCount;

	/**
	 * No. Of Comments for this Feed
	 */
	private long commentsCount;

	/**
	 * Array of IDs of Images
	 */
	private String[] images;

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName
	 *            the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return the authorId
	 */
	public String getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId
	 *            the authorId to set
	 */
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the likesCount
	 */
	public long getLikesCount() {
		return likesCount;
	}

	/**
	 * @param likesCount
	 *            the likesCount to set
	 */
	public void setLikesCount(long likesCount) {
		this.likesCount = likesCount;
	}

	/**
	 * @return the sharesCount
	 */
	public long getSharesCount() {
		return sharesCount;
	}

	/**
	 * @param sharesCount
	 *            the sharesCount to set
	 */
	public void setSharesCount(long sharesCount) {
		this.sharesCount = sharesCount;
	}

	/**
	 * @return the commentsCount
	 */
	public long getCommentsCount() {
		return commentsCount;
	}

	/**
	 * @param commentsCount
	 *            the commentsCount to set
	 */
	public void setCommentsCount(long commentsCount) {
		this.commentsCount = commentsCount;
	}

	/**
	 * @return the images
	 */
	public String[] getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(String[] images) {
		this.images = images;
	}

	/**
	 * @return the facebookId
	 */
	public String getFacebookId() {
		return facebookId;
	}

	/**
	 * @param facebookId
	 *            the facebookId to set
	 */
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	/**
	 * @return the gPlusId
	 */
	public String getgPlusId() {
		return gPlusId;
	}

	/**
	 * @param gPlusId
	 *            the gPlusId to set
	 */
	public void setgPlusId(String gPlusId) {
		this.gPlusId = gPlusId;
	}

	/**
	 * @return the twitterId
	 */
	public String getTwitterId() {
		return twitterId;
	}

	/**
	 * @param twitterId
	 *            the twitterId to set
	 */
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

}
