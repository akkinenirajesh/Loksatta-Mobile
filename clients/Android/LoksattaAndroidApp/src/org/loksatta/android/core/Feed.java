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
	private String authodId;

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
	private String fbId;

	/**
	 * Google Plus Id
	 */
	private String gId;

	/**
	 * Id of the Twitter
	 */
	private String tId;

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
	 * @return the authodId
	 */
	public String getAuthodId() {
		return authodId;
	}

	/**
	 * @param authodId
	 *            the authodId to set
	 */
	public void setAuthodId(String authodId) {
		this.authodId = authodId;
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
	 * @return the fbId
	 */
	public String getFbId() {
		return fbId;
	}

	/**
	 * @param fbId
	 *            the fbId to set
	 */
	public void setFbId(String fbId) {
		this.fbId = fbId;
	}

	/**
	 * @return the gId
	 */
	public String getgId() {
		return gId;
	}

	/**
	 * @param gId
	 *            the gId to set
	 */
	public void setgId(String gId) {
		this.gId = gId;
	}

	/**
	 * @return the tId
	 */
	public String gettId() {
		return tId;
	}

	/**
	 * @param tId
	 *            the tId to set
	 */
	public void settId(String tId) {
		this.tId = tId;
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

}
