package org.loksatta.android.core;

public class Photo extends AbstractCore {

	/**
	 * Title of the photo
	 */
	private String title;

	/**
	 * Name of the Author of this photo(if any)
	 */
	private String authorName;

	/**
	 * Id of the Author of this photo(if any)
	 */
	private String authorId;

	/**
	 * Id of the Facebook
	 */
	private String fbId;

	/**
	 * Id of the Google Plus
	 */
	private String gId;

	/**
	 * Id of the Twitter
	 */
	private String tId;

	/**
	 * No. Of Likes for this Photo
	 */
	private String likesCount;

	/**
	 * No. of shares for this photo
	 */
	private String sharesCount;

	/**
	 * No. Of comments for this Photo
	 */
	private String commentsCount;

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
	public String getLikesCount() {
		return likesCount;
	}

	/**
	 * @param likesCount
	 *            the likesCount to set
	 */
	public void setLikesCount(String likesCount) {
		this.likesCount = likesCount;
	}

	/**
	 * @return the sharesCount
	 */
	public String getSharesCount() {
		return sharesCount;
	}

	/**
	 * @param sharesCount
	 *            the sharesCount to set
	 */
	public void setSharesCount(String sharesCount) {
		this.sharesCount = sharesCount;
	}

	/**
	 * @return the commentsCount
	 */
	public String getCommentsCount() {
		return commentsCount;
	}

	/**
	 * @param commentsCount
	 *            the commentsCount to set
	 */
	public void setCommentsCount(String commentsCount) {
		this.commentsCount = commentsCount;
	}

}
